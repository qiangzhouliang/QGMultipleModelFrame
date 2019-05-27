package qzl.com.main.domain

/**
 * @desc 网络请求二封装-实体
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-05-27 15:25
 * @class QGMultipleModelFrame
 * @package qzl.com.main.domain
 */
data class Net2Bean(
    val `data`: Data
)

data class Data(
    val result: List<Result>,
    val sql: String,
    val totalPage: Int,
    val totalRow: Int
)

data class Result(
    val ADDRESS: String,
    val ADMIN_PUNISH: Any,
    val AP_CASE_NUM: Any,
    val AREA_CODE: String,
    val AREA_GT_THOUSAND: String,
    val AREA_NAME: String,
    val BUILD_AREA: Any,
    val BUILD_NAME: Any,
    val BUILD_TYPE: Any,
    val BUILD_UNIT: Any,
    val CANCEL_TIME: Any,
    val CANCEL_USER_ID: Any,
    val CANCEL_USER_NAME: Any,
    val CHECK_SECOND_TYPE: Any,
    val CHECK_THIRD_TYPE: String,
    val CHECK_VAL1: Any,
    val CHECK_VAL2: Any,
    val CITY_HEAD_NAME: Any,
    val CITY_HEAD_POST: Any,
    val CLEAR_SECOND_TYPE: Any,
    val CLEAR_THIRD_TYPE: Any,
    val CLEAR_VAL1: Any,
    val CLEAR_VAL10: Any,
    val CLEAR_VAL11: Any,
    val CLEAR_VAL12: Any,
    val CLEAR_VAL13: Any,
    val CLEAR_VAL2: Any,
    val CLEAR_VAL3: Any,
    val CLEAR_VAL4: Any,
    val CLEAR_VAL5: Any,
    val CLEAR_VAL6: Any,
    val CLEAR_VAL7: Any,
    val CLEAR_VAL8: Any,
    val CLEAR_VAL9: Any,
    val COUNTY_HEAD_NAME: String,
    val COUNTY_HEAD_POST: Any,
    val CREATE_TIME: String,
    val EXAM_PROC: Any,
    val EXAM_UNIT: Any,
    val FIRST_TYPE: String,
    val GOVERN_SITUATION: Any,
    val HEAD_NAME: String,
    val HEAD_POST: Any,
    val INVOLVED_NUM: Any,
    val LAT: String,
    val LON: String,
    val PK_ID: String,
    val PROBLEM_DESC: String,
    val REACH_CODE: Any,
    val REACH_NAME: String,
    val REFORM: Any,
    val REFORM_TIME: Any,
    val REMARKS: Any,
    val RIVER_CODE: Any,
    val RIVER_LEVEL: Any,
    val RIVER_NAME: String,
    val RIVER_TYPE: Any,
    val SECOND_TYPE: Any,
    val STATE: String,
    val TJ_CASE_NUM: Any,
    val TRANS_JUD: Any,
    val VILLAGE_HEAD_NAME: String,
    val VILLAGE_HEAD_POST: Any
)