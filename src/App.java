import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import ai.onnxruntime.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("주식 추천 시스템");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 1000);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 147, 255));
        JLabel headerLabel = new JLabel("주식 추천 시스템");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel stockNameLabel = new JLabel("주식 종목명:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(stockNameLabel, gbc);

        JTextField stockNameField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(stockNameField, gbc);

        String[] inputLabels = {
            "주가 (종가):", "고가:", "저가:", "EPS:", "PER:",
            "외국인 보유수량:", "기관 합계:", "기타법인:", "개인:", "외국인 합계:"
        };

        JTextField[] inputFields = new JTextField[inputLabels.length];
        for (int i = 0; i < inputLabels.length; i++) {
            JLabel label = new JLabel(inputLabels[i]);
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            mainPanel.add(label, gbc);

            inputFields[i] = new JTextField(20);
            gbc.gridx = 1;
            mainPanel.add(inputFields[i], gbc);
        }

        JButton analyzeButton = new JButton("분석하기");
        gbc.gridx = 0;
        gbc.gridy = inputLabels.length + 1;
        gbc.gridwidth = 2;
        mainPanel.add(analyzeButton, gbc);

        JLabel resultLabel = new JLabel("결과가 여기에 표시됩니다.");
        resultLabel.setForeground(new Color(0, 128, 0));
        gbc.gridy = inputLabels.length + 2;
        mainPanel.add(resultLabel, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String stockName = stockNameField.getText().trim();
                    
                    String rfClassModelPath = "models/" + stockName + "_rf_class.onnx";
                    String rfRegModelPath = "models/" + stockName + "_rf_reg.onnx";
                    String abClassModelPath = "models/" + stockName + "_ab_class.onnx";
                    String abRegModelPath = "models/" + stockName + "_ab_reg.onnx";

                    if (!new java.io.File(rfClassModelPath).exists() || 
                        !new java.io.File(rfRegModelPath).exists() || 
                        !new java.io.File(abClassModelPath).exists() || 
                        !new java.io.File(abRegModelPath).exists()) {
                        resultLabel.setText("해당 주식 모델이 없습니다: " + stockName);
                        resultLabel.setForeground(Color.RED);
                        return;
                    }
        
                    float[] inputFeatures = new float[inputLabels.length];
                    for (int i = 0; i < inputLabels.length; i++) {
                        try {
                            inputFeatures[i] = Float.parseFloat(inputFields[i].getText().trim().replace(",", ""));
                        } catch (NumberFormatException ex) {
                            resultLabel.setText(inputLabels[i] + "는 숫자로 입력하세요(콤마 포함 가능)");
                            resultLabel.setForeground(Color.RED);
                            return;
                        }
                    }
        
                    float currentPrice = inputFeatures[0]; // User input for current stock price
        
                    String rfClassResult = predictWithOnnx(rfClassModelPath, inputFeatures, true);
                    float rfRegResult = Float.parseFloat(predictWithOnnx(rfRegModelPath, inputFeatures, false));
                    String abClassResult = predictWithOnnx(abClassModelPath, inputFeatures, true);
                    float abRegResult = Float.parseFloat(predictWithOnnx(abRegModelPath, inputFeatures, false));
        
                    float rfPredictedPrice = currentPrice + rfRegResult;
                    float abPredictedPrice = currentPrice + abRegResult;
        
                    
                    String rfFinalRecommendation = rfClassResult.equals("1") ? "추천" : "비추천";
                    if (rfFinalRecommendation.equals("추천") && rfRegResult < 0) {
                        rfFinalRecommendation = "비추천";
                        // rfRegResult = Math.abs(rfRegResult); // Treat as warning case
                    }
        
                    String abFinalRecommendation = abClassResult.equals("1") ? "추천" : "비추천";
                    if (abFinalRecommendation.equals("추천") && abRegResult < 0) {
                        abFinalRecommendation = "비추천";
                        // abRegResult = Math.abs(abRegResult); // Treat as warning case
                    }
        
                    String result = String.format(
                        "<html>주식: %s<br>" +
                        "랜덤 포레스트<br>" +
                        "- 추천: %s<br>" +
                        "- 예상 변동 폭: %.2f<br>" +
                        "- 예상 주가: %.2f<br>" +
                        "애드부스트<br>" +
                        "- 추천: %s<br>" +
                        "- 예상 변동 폭: %.2f<br>" +
                        "- 예상 주가: %.2f<br>" +
                        "</html>",
                        stockName,
                        rfFinalRecommendation,
                        rfRegResult,
                        rfPredictedPrice,
                        abFinalRecommendation,
                        abRegResult,
                        abPredictedPrice
                    );
        
                    resultLabel.setText(result);
                    resultLabel.setForeground(new Color(0, 128, 0));
                } catch (Exception ex) {
                    resultLabel.setText("오류 발생: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        

        frame.setVisible(true);
    }

    public static String predictWithOnnx(String modelPath, float[] inputFeatures, boolean isClassification) throws Exception {
        try (OrtEnvironment env = OrtEnvironment.getEnvironment();
             OrtSession session = env.createSession(modelPath)) {

            float[][] inputTensorData = new float[1][inputFeatures.length];
            System.arraycopy(inputFeatures, 0, inputTensorData[0], 0, inputFeatures.length);

            OnnxTensor inputTensor = OnnxTensor.createTensor(env, inputTensorData);
            OrtSession.Result result = session.run(Collections.singletonMap("float_input", inputTensor));

            Object value = result.get(0).getValue();

            if (isClassification) {
                long[] predictions = (long[]) value;
                return String.valueOf(predictions[0]);
            } else {
                float[][] predictions = (float[][]) value;
                return String.valueOf(predictions[0][0]);
            }
        }
    }
}
