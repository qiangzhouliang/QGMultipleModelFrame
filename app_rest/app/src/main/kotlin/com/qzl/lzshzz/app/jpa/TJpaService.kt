package com.qzl.lzshzz.app.jpa

import com.qzl.lzshzz.model.ucenter.SysAccount
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * @ClassName TJpaService
 * @Description
 * @Author qzl
 * @Date 2019/11/14 16:24
 */
@Service
class TJpaService {
    @Autowired
    var cloudServerRepository: CloudServerRepository? = null

    fun getData(key: String?, userId: Int?, currentPage: Int?, pageSize: Int?): Page<SysAccount> { //排序
        val orders: MutableList<Sort.Order> = ArrayList()
        orders.add(Sort.Order(Sort.Direction.ASC, "userAcctId"))
        orders.add(Sort.Order(Sort.Direction.DESC, "loginAccount"))
        val pageable: Pageable = PageRequest.of(currentPage!!, pageSize!!, Sort(orders))
        return cloudServerRepository!!.findAll(Specification { root: Root<SysAccount?>, query: CriteriaQuery<*>?, criteriaBuilder: CriteriaBuilder ->
            val list: MutableList<Predicate> = ArrayList()
            //            Predicate onePredicate = criteriaBuilder.equal(root.get("id"), "");
//            Predicate towPredicate = criteriaBuilder.equal(root.get("status"), "");
//            Predicate threePredicate = criteriaBuilder.or(onePredicate, towPredicate);//这个是让这两个条件进行or的连接。
//            list.add(threePredicate);
// 第一个userId为CloudServerDao中的字段，第二个userId为参数
//            Predicate p1 = criteriaBuilder.equal(root.get("userAcctId"),userId);
//            list.add(p1);
            if (key != null) { // 此处为查询serverName中含有key的数据
                val p2 = criteriaBuilder.like(root.get("userName"), "%$key%")
                list.add(p2)
            }
            val p3 = criteriaBuilder.like(root.get("userTele"), "%" + 1391942 + "%")
            list.add(p3)
            criteriaBuilder.and(*list.toTypedArray())
        }, pageable)
    }
}