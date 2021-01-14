package com.zhboy.retrofit2_coroutine.bean

/**
 * @author: zhou_hao
 * @date: 2021/1/13
 * @description:
 */
class GoodsBean {
    /**
     * state : 1
     * message : success
     * data : [{"id":1,"title":"N95口罩","contentDesc":"N95口罩","landingPage":"https://s.click.taobao.com/xUIWSsu","isDelete":0,"goodStatus":1,"createTime":"2020-10-20T03:46:55.000+0000","updateTime":"2021-01-08T07:51:20.000+0000","taskBaseId":"see_special_goods"},{"id":2,"title":"一次性口罩","contentDesc":"一次性口罩","landingPage":"https://s.click.taobao.com/qTakHsu","isDelete":0,"goodStatus":1,"createTime":"2020-11-25T09:48:55.000+0000","updateTime":"2021-01-11T03:21:53.000+0000","taskBaseId":"see_special_goods02"},{"id":3,"title":"牙刷","contentDesc":"牙刷","landingPage":"https://s.click.taobao.com/wpKXNsu","isDelete":0,"goodStatus":1,"createTime":"2020-11-25T09:49:02.000+0000","updateTime":"2021-01-08T07:49:26.000+0000","taskBaseId":"see_special_goods03"}]
     */
    var state: Int? = null
    var message: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 1
         * title : N95口罩
         * contentDesc : N95口罩
         * landingPage : https://s.click.taobao.com/xUIWSsu
         * isDelete : 0
         * goodStatus : 1
         * createTime : 2020-10-20T03:46:55.000+0000
         * updateTime : 2021-01-08T07:51:20.000+0000
         * taskBaseId : see_special_goods
         */
        var id: Int? = null
        var title: String? = null
        var contentDesc: String? = null
        var landingPage: String? = null
        var isDelete: Int? = null
        var goodStatus: Int? = null
        var createTime: String? = null
        var updateTime: String? = null
        var taskBaseId: String? = null
    }
}