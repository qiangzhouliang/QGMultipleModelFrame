package com.zdww.lzshzz.common.web

import com.zdww.lzshzz.util.StringHelper
import org.springframework.data.domain.Sort
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


class PageSpecOrSort<RESPONSE> {
    //获取排序参数 参数形式 ASC_XXX,多个参数用，隔开
    fun getSort(vararg args: String): Sort {
        val orders: MutableList<Sort.Order> = ArrayList()
        args.forEach {
            val split = it.split("_")
            if ("DESC" == split[0]){
                orders.add(Sort.Order(Sort.Direction.DESC, split[1]))
            } else {
                orders.add(Sort.Order(Sort.Direction.ASC, split[1]))
            }
        }
        return Sort(orders)
    }

    //构造查询参数
    fun getSpecification(map: HashMap<String,String?>): (Root<RESPONSE?>, CriteriaQuery<*>?, CriteriaBuilder) -> Predicate {
        return { root: Root<RESPONSE?>, query: CriteriaQuery<*>?, criteriaBuilder: CriteriaBuilder ->
            val list: MutableList<Predicate> = ArrayList()
            map.forEach {
                val split = it.key.split("_")
                when (split[0]) {
                    "EQ" -> list.add(criteriaBuilder.equal(root.get<String>(split[1]),it.value))
                    "NEQ" -> list.add(criteriaBuilder.notEqual(root.get<String>(split[1]),it.value))
                    "LIKE" -> list.add(criteriaBuilder.like(root.get<String>(split[1]),"%${it.value}%"))
                    "IN" -> {
                        //先创建in
                        val valueIn = criteriaBuilder.`in`(root.get<String>(split[1]))
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        split1?.forEach{
                            valueIn.value(it)
                        }
                        list.add(valueIn)
                    }
                    "NIN" -> {
                        //先创建in
                        val nIn = criteriaBuilder.`in`(root.get<String>(split[1]))
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        split1?.forEach{ str->
                            nIn.value(str)
                        }
                        list.add(criteriaBuilder.not(nIn))
                    }
                    "BETWEEN" -> {
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        if (split1?.size == 2 && !StringHelper.isEmptyString(split1[0]) && !StringHelper.isEmptyString(split1[1])) {
                            list.add(criteriaBuilder.between(root.get<String>(split[1]), split1[0], split1[1]))
                        }
                    }
                    "NOTNULL" -> list.add(criteriaBuilder.isNotNull(root.get<String>(split[1])))
                    "ISNULL" -> list.add(criteriaBuilder.isNull(root.get<String>(split[1])))
                }
            }
            criteriaBuilder.and(*list.toTypedArray())
        }
    }

    //带有or 和 and 连接参数的
    fun getSpecificationOrAnd(mapAnd: HashMap<String,String?>,mapOr: HashMap<String,String?>): (Root<RESPONSE?>, CriteriaQuery<*>?, CriteriaBuilder) -> Predicate {
        return { root: Root<RESPONSE?>, query: CriteriaQuery<*>?, builder: CriteriaBuilder ->
            val listAnd: MutableList<Predicate> = ArrayList()
            mapAnd.forEach {
                val split = it.key.split("_")
                when (split[0]) {
                    "EQ" -> listAnd.add(builder.equal(root.get<String>(split[1]),it.value))
                    "NEQ" -> listAnd.add(builder.notEqual(root.get<String>(split[1]),it.value))
                    "LIKE" -> listAnd.add(builder.like(root.get<String>(split[1]),"%${it.value}%"))
                    "IN" -> {
                        //先创建in
                        val valueIn = builder.`in`(root.get<String>(split[1]))
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        split1?.forEach{ str->
                            valueIn.value(str)
                        }
                        listAnd.add(valueIn)
                    }
                    "NIN" -> {
                        //先创建in
                        val nIn = builder.`in`(root.get<String>(split[1]))
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        split1?.forEach{ str->
                            nIn.value(str)
                        }
                        listAnd.add(builder.not(nIn))
                    }
                    "BETWEEN" -> {
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        if (split1?.size == 2 && !StringHelper.isEmptyString(split1[0]) && !StringHelper.isEmptyString(split1[1])) {
                            listAnd.add(builder.between(root.get<String>(split[1]), split1[0], split1[1]))
                        }
                    }
                    "NOTNULL" -> listAnd.add(builder.isNotNull(root.get<String>(split[1])))
                    "ISNULL" -> listAnd.add(builder.isNull(root.get<String>(split[1])))
                }
            }
            val listOr: MutableList<Predicate> = ArrayList()
            mapOr.forEach {
                if (StringHelper.isEmptyString(it.value)) return@forEach
                val split = it.key.split("_")
                when (split[0]) {
                    "EQ" -> listOr.add(builder.equal(root.get<String>(split[1]),it.value))
                    "NEQ" -> listOr.add(builder.notEqual(root.get<String>(split[1]),it.value))
                    "LIKE" -> listOr.add(builder.like(root.get<String>(split[1]),"%${it.value}%"))
                    "IN" -> {
                        //先创建in
                        val valueIn = builder.`in`(root.get<String>(split[1]))
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        if (split1?.isNotEmpty()!!){
                            split1.forEach{
                                valueIn.value(it)
                            }
                        }
                        listOr.add(valueIn)
                    }
                    "NIN" -> {
                        //先创建in
                        val nIn = builder.`in`(root.get<String>(split[1]))
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        if (split1?.isNotEmpty()!!){
                            split1.forEach{ str->
                                nIn.value(str)
                            }
                        }
                        listOr.add(builder.not(nIn))
                    }
                    "BETWEEN" -> {
                        //获取所需的集合
                        val split1 = it.value?.split(",")
                        if (split1?.size == 2 && !StringHelper.isEmptyString(split1[0]) && !StringHelper.isEmptyString(split1[1])) {
                            listOr.add(builder.between(root.get<String>(split[1]), split1[0], split1[1]))
                        }
                    }
                    "NOTNULL" -> listOr.add(builder.isNotNull(root.get<String>(split[1])))
                    "ISNULL" -> listOr.add(builder.isNull(root.get<String>(split[1])))
                }
            }

            // 将所有条件用 and 联合起来
            if (listOr.size > 0 && listAnd.size==0) {
                builder.or(*listOr.toTypedArray())
            } else if (listOr.size == 0 && listAnd.size > 0) {
                builder.and(*listAnd.toTypedArray())
            } else if(listOr.size  > 0 && listAnd.size > 0){
                builder.and(builder.and(*listAnd.toTypedArray()),builder.or(*listOr.toTypedArray()))
            } else{
                builder.and()
            }
        }
    }
}