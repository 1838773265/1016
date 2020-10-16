package com.example.p2p.entity;

public class GenXinEntity {


    /**
     * code : 200
     * msg : 请求成功
     * result : {"versionCode":1,"versionName":"1.0","apkUrl":"http://49.233.93.155:8080/atguigu/apk/P2PInvest/app-release.apk","desc":"解决一些bug, 优化网络请求!","flag":true}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * versionCode : 1
         * versionName : 1.0
         * apkUrl : http://49.233.93.155:8080/atguigu/apk/P2PInvest/app-release.apk
         * desc : 解决一些bug, 优化网络请求!
         * flag : true
         */

        private int versionCode;
        private String versionName;
        private String apkUrl;
        private String desc;
        private boolean flag;

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
