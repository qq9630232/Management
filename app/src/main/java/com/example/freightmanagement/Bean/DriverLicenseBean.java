package com.example.freightmanagement.Bean;

/**
 * Created by songdechuan on 2020/8/14.
 */

public class DriverLicenseBean {


    private WordsResultBean words_result;
    private long log_id;
    private int words_result_num;
    private int direction;

    public WordsResultBean getWords_result() {
        return words_result;
    }

    public void setWords_result(WordsResultBean words_result) {
        this.words_result = words_result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public static class WordsResultBean {
        private 姓名Bean 姓名;
        private 至Bean 至;
        private 出生日期Bean 出生日期;
        private 证号Bean 证号;
        private 住址Bean 住址;
        private 初次领证日期Bean 初次领证日期;
        private 国籍Bean 国籍;
        private 准驾车型Bean 准驾车型;
        private 性别Bean 性别;
        private 有效期限Bean 有效期限;
        private 有效起始日期Bean 有效起始日期;

        public 姓名Bean get姓名() {
            return 姓名;
        }

        public void set姓名(姓名Bean 姓名) {
            this.姓名 = 姓名;
        }

        public 至Bean get至() {
            return 至;
        }

        public void set至(至Bean 至) {
            this.至 = 至;
        }

        public 出生日期Bean get出生日期() {
            return 出生日期;
        }

        public void set出生日期(出生日期Bean 出生日期) {
            this.出生日期 = 出生日期;
        }

        public 证号Bean get证号() {
            return 证号;
        }

        public void set证号(证号Bean 证号) {
            this.证号 = 证号;
        }

        public 住址Bean get住址() {
            return 住址;
        }

        public void set住址(住址Bean 住址) {
            this.住址 = 住址;
        }

        public 初次领证日期Bean get初次领证日期() {
            return 初次领证日期;
        }

        public void set初次领证日期(初次领证日期Bean 初次领证日期) {
            this.初次领证日期 = 初次领证日期;
        }

        public 国籍Bean get国籍() {
            return 国籍;
        }

        public void set国籍(国籍Bean 国籍) {
            this.国籍 = 国籍;
        }

        public 准驾车型Bean get准驾车型() {
            return 准驾车型;
        }

        public void set准驾车型(准驾车型Bean 准驾车型) {
            this.准驾车型 = 准驾车型;
        }

        public 性别Bean get性别() {
            return 性别;
        }

        public void set性别(性别Bean 性别) {
            this.性别 = 性别;
        }

        public 有效期限Bean get有效期限() {
            return 有效期限;
        }

        public void set有效期限(有效期限Bean 有效期限) {
            this.有效期限 = 有效期限;
        }

        public 有效起始日期Bean get有效起始日期() {
            return 有效起始日期;
        }

        public void set有效起始日期(有效起始日期Bean 有效起始日期) {
            this.有效起始日期 = 有效起始日期;
        }

        public static class 姓名Bean {
            /**
             * words : 宋德川
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 至Bean {
            /**
             * words : 20210825
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 出生日期Bean {
            /**
             * words : 19960321
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 证号Bean {
            /**
             * words : 41062219960321201X
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 住址Bean {
            /**
             * words : 河南省淇县高村镇花庄韦六路13号
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 初次领证日期Bean {
            /**
             * words : 20150825
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 国籍Bean {
            /**
             * words : 中国
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 准驾车型Bean {
            /**
             * words : C1
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 性别Bean {
            /**
             * words : 男
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 有效期限Bean {
            /**
             * words : 10
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 有效起始日期Bean {
            /**
             * words : 20150825
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }
    }
}
