public class accuracy {
    String stockName;
    //정확도 메타데이터 기반
    double output_rf(String stockName) {
        this.stockName = stockName;
        switch (stockName) {
            case "삼성전자":
                return 0.8088235294117647;
            case "LG전자":
                return 0.8783783783783784;
            case "SK하이닉스":
                return 0.9047619047619048;
            case "기업은행":
                return 0.7727272727272727;

            case "기아":
                return 0.7910447761194029;
                
            case "카카오":
                return 0.7894736842105263;
                 
            case "네이버":
                return 92.0;
                 
            case "대한항공":
                return 0.532258064516129;
                 
            case "고려아연":
                return 0.7538461538461538;
                 
            case "현대차":
                return 0.8676470588235294;
                 
            case "삼성물산":
                return 0.8405797101449275;
                 
            case "삼성생명":
                return 0.8823529411764706;
                 
            case "두산에너지빌리티":
                return 0.8378378378378378;
                 
            case "메리츠금융지주":
                return 0.7466666666666667;
                 
            case "삼성바이오로직스":
                return 0.8181818181818182;
                 
            case "삼성전자우":
                return 0.6;
                 
            case "삼성중공업":
                return 0.8787878787878788;
                 
            case "삼성화재":
                return 0.8552631578947368;
                 
            case "삼성SDI":
                return 0.9069767441860465;
                 
            case "셀트리온":
                return 0.8656716417910447;
                 
            case "카카오뱅크":
                return 0.7910447761194029;
                 
            case "신한지주":
                return 0.796875;
                 
            case "우리금융지주":
                return 0.8208955223880597;
                 
            case "유한양행":
                return 0.703125;
                 
            case "크래프톤":
                return 0.7466666666666667;
                 
            case "포스코퓨처엠":
                return 0.7654320987654321;
                 
            case "하나금융지주":
                return 0.8028169014084507;
                 
            case "하이브":
                return 0.8787878787878788;
                 
            case "한국전력":
                return 0.7733333333333333;
                 
            case "한화에어로스페이스":
                return 0.7083333333333334;
                 
            case "한화오션":
                return 0.7846153846153846;
                 
            case "현대글로비스":
                return 0.8153846153846154;
                 
            case "현대모비스":
                return 0.8307692307692308;
                 
            case "HD한국조선해양":
                return 0.8243243243243243;
                 
            case "HD현대일레트릭":
                return 0.810126582278481;
                 
            case "HD현대중공업":
                return 0.7671232876712328;
                 
            case "HMM":
                return 0.875;
                 
            case "KB금융":
                return 0.84375;
                 
            case "KT":
                return 0.7619047619047619;
                 
            case "KT&G":
                return 0.7671232876712328;
                 
            case "LG":
                return 0.8309859154929577;
                 
            case "LG에너지솔루션":
                return 0.8;
                 
            case "LG화학":
                return 0.8571428571428571;
                 
            case "NAVER":
                return 0.625;
                 
            case "POSCO홀딩스":
                return 0.8;
                 
            case "SK이노베이션":
                return 0.8333333333333334;
                 
            case "SK":
                return 0.7534246575342466;
                 
            case "SK텔레콤":
                return 0.7222222222222222;

            case "SK스퀘어":
                return 0.8181818181818182;  
                 
            default:
                return 0.0;
        }
    }
    double output_ad(String stockName) {
        this.stockName = stockName;
        switch (stockName) {
            case "삼성전자":
                return 0.8382352941176471;
            case "LG전자":
                return 0.6891891891891891;
            case "SK하이닉스":
                return 0.7619047619047619;
            case "기업은행":
                return 0.6818181818181818;
            case "기아":
                return 0.8059701492537313;
                
            case "카카오":
                return 0.6578947368421053;
                 
            case "네이버":
                return 92.0;
                 
            case "대한항공":
                return 0.6774193548387096;
                 
            case "고령아연":
                return 0.5846153846153846;
                 
            case "현대차":
                return 0.8823529411764706;
                 
            case "삼성물산":
                return 0.8260869565217391;
                 
            case "삼성생명":
                return 0.6911764705882353;
                 
            case "두산에너지빌리티":
                return 0.581081081081081;
                 
            case "메리츠금융지주":
                return 0.7066666666666667;
                 
            case "삼성바이오로직스":
                return 0.7272727272727273;
                 
            case "삼성전자우":
                return 0.5333333333333333;
                 
            case "삼성중공업":
                return 0.696969696969697;
                 
            case "삼성화재":
                return 0.7894736842105263;
                 
            case "삼성SDI":
                return 0.8953488372093024;
                 
            case "셀트리온":
                return 0.7611940298507462;
                 
            case "카카오뱅크":
                return 0.6865671641791045;
                 
            case "신한지주":
                return 0.796875;
                 
            case "우리금융지주":
                return 0.8208955223880597;
                 
            case "유한양행":
                return 0.65625;
                 
            case "크래프톤":
                return 0.6133333333333333;
                 
            case "포스코퓨처엠":
                return 0.7160493827160493;
                 
            case "하나금융지주":
                return 0.6619718309859155;
                 
            case "하이브":
                return 0.7424242424242424;
                 
            case "한국전력":
                return 0.7066666666666667;
                 
            case "한화에어로스페이스":
                return 0.6527777777777778;
                 
            case "한화오션":
                return 0.7692307692307693;
                 
            case "현대글로비스":
                return 0.8;
                 
            case "현대모비스":
                return 0.6615384615384615;
                 
            case "HD한국조선해양":
                return 0.7297297297297297;
                 
            case "HD현대일레트릭":
                return 0.7848101265822784;
                 
            case "HD현대중공업":
                return 0.7397260273972602;
                 
            case "HMM":
                return 0.8055555555555556;
                 
            case "KB금융":
                return 0.78125;
                 
            case "KT":
                return 0.6349206349206349;
                 
            case "KT&G":
                return 0.7123287671232876;
                 
            case "LG":
                return 0.7605633802816901;
                 
            case "LG에너지솔루션":
                return 0.7230769230769231;
                 
            case "LG화학":
                return 0.7285714285714285;
                 
            case "NAVER":
                return 0.484375;
                 
            case "POSCO홀딩스":
                return 0.76;
                 
            case "SK이노베이션":
                return 0.6944444444444444;
                 
            case "SK":
                return 0.7397260273972602;
                 
            case "SK텔레콤":
                return 0.6527777777777778;
            case "SK스퀘어":
                return 0.6818181818181818;
                 
            default:
                return 0.0;
        }
    }

}
