package com.hbnu.test;

import com.hbnu.pojo.*;
import com.hbnu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author luanhao
 * @date 2023年02月22日 10:10
 */
public class HibernateTest {

    /*
    往数据库表中添加一条数据
    * */
    @Test
    public void interData() {
//        1、解析并加载配置文件(直接加载核心配置文件，间接加载映射关系配置文件)
        Configuration config = new Configuration().configure();

//        2、通过配置对象构建会话工厂
        SessionFactory sessionFactory = config.buildSessionFactory();

//        3、通过会话工厂生成会话对象
        Session session = sessionFactory.openSession();

//        4、通过会话对象调用相关方法完成数据库的CRUD操作
        User user = new User();
        user.setUsername("栾皓");
        user.setAddress("湖北师范大学");

        session.save(user); //通过操作实例对象的方式操作数据库

//        5、关闭会话和会话工厂资源
        session.close();
        sessionFactory.close();
    }

    @Test
    public void queryData() {
        Session session = HibernateUtil.openSession();

        User user = session.get(User.class, 1);
        System.out.println(user);

        HibernateUtil.release(session);
    }

    @Test
    public void updateData() {
        Session session = HibernateUtil.openSession();

//        只要对数据库表中的数据做了改变，则需要添加事务处理操作
        Transaction transaction = session.beginTransaction();//开启事务

        User user = session.get(User.class, 1);
        user.setAddress("hbnu");
        session.update(user);

        transaction.commit();//提交事务
//        transaction.rollback();//事务回滚

        HibernateUtil.release(session);
    }

    @Test
    public void deleteData() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 1);
        session.delete(user);

        transaction.commit();

        HibernateUtil.release(session);
    }

    @Test
    public void saveOrUpdateTest() {
        Session session = HibernateUtil.openSession();

/*
//        瞬时态:无id，与session无关联
        User user = new User();
        user.setUsername("栾小白");
        user.setAddress("湖北黄石");
        session.saveOrUpdate(user);//对于瞬时态对象，如果使用saveOrUodate方法操作对象，则执行的是添加操作
*/

/*
//        托管态
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setuId(2);
        user.setUsername("栾小胖");
        user.setAddress("湖北孝感");
        session.saveOrUpdate(user);//对于托管态对象，使用saveOrUpdate方法操作时，执行的是更新操作，同时托管态对象的id必须在数据库表中已存在

        transaction.commit();
*/

//        持久态
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 2);
        user.setUsername("栾小胖");
        user.setAddress("湖北孝感");
//        session.saveOrUpdate(user);//对于持久态对象，使用saveOrUpdate方法操作时，执行的是更新操作。不执行savaOrUpdate方法，持久态对象也会更新

        transaction.commit();

        HibernateUtil.release(session);

    }

    /*
     * 验证一级缓存的存在
     * */
    @Test
    public void validateCache() {
        Session session = HibernateUtil.openSession();

        User user1 = session.get(User.class, 2);
        System.out.println(user1);

        User user2 = session.get(User.class, 2);
        System.out.println(user2);

        HibernateUtil.release(session);
    }

    @Test
    public void queryTwo() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        String HQL = "select username, address from User";
        Query<Object[]> query = session.createQuery(HQL, Object[].class);
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(o[0] + "-->" + o[1]);
        }

        HibernateUtil.release(session);
    }

    /*
     * 条件查询
     * */
    @Test
    public void conditionalQuery() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

/*
        String HQL = "from User where username = ?0 and address = ?1";
        Query<User> query = session.createQuery(HQL, User.class);
        query.setParameter(0, "栾皓");
        query.setParameter(1, "湖北师范大学");
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }
*/
        String HQL = "from User where username = :username and address = :address";
        Query<User> query = session.createQuery(HQL, User.class);
        query.setParameter("username", "栾皓");
        query.setParameter("address", "湖北师范大学");
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        HibernateUtil.release(session);
    }

    @Test
    public void testCriteria() {
        Session session = HibernateUtil.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

/*
//        查询所有字段
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.from(User.class);
        Query<User> query = session.createQuery(criteriaQuery);
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }
        */

/*
//        查询某一具体字段
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<User> root = criteriaQuery.from(User.class);//查询的根
        criteriaQuery.select(root.get("username"));
        Query<String> query = session.createQuery(criteriaQuery);
        List<String> userList = query.list();
        for (String username : userList) {
            System.out.println(username);
        }
*/

//        条件查询
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"), "栾小胖"));
        Query<User> query = session.createQuery(criteriaQuery);
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        HibernateUtil.release(session);
    }

    @Test
    public void testSQLQuery() {
        Session session = HibernateUtil.openSession();

/*//        查询所有字段的数据
        String sql = "select * from tb_user";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(User.class);
        List<User> userList = sqlQuery.list();
        for (User user : userList) {
            System.out.println(user);
        }*/

/*//        查询某一具体字段
        String sql = "select name from tb_user";
        NativeQuery<String> sqlQuery = session.createSQLQuery(sql);
        List<String> usernameList = sqlQuery.list();
        for (String username : usernameList) {
            System.out.println(username);
        }*/

//        条件查询
        String sql = "select * from tb_user where name = ? and address = ?";
        NativeQuery<User> sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(User.class);
        sqlQuery.setParameter(1, "栾小胖");
        sqlQuery.setParameter(2, "湖北孝感");
        List<User> userList = sqlQuery.list();
        for (User user : userList) {
            System.out.println(user);
        }

        HibernateUtil.release(session);
    }

    @Test
    public void testOneToManySaveTest() {
        Session session = HibernateUtil.openSession();

        Customer customer1 = new Customer();
        customer1.setName("百度");
        customer1.setAddress("北京");

        Customer customer2 = new Customer();
        customer2.setName("腾讯");
        customer2.setAddress("深圳");

        LinkMan linkMan1 = new LinkMan();
        linkMan1.setName("笑川");
        linkMan1.setTel("15349");
        linkMan1.setGender("男");

        LinkMan linkMan2 = new LinkMan();
        linkMan2.setName("小马");
        linkMan2.setTel("13579");
        linkMan2.setGender("男");

        LinkMan linkMan3 = new LinkMan();
        linkMan3.setName("小额");
        linkMan3.setTel("24680");
        linkMan3.setGender("男");

        LinkMan linkMan4 = new LinkMan();
        linkMan4.setName("拟态");
        linkMan4.setTel("75913");
        linkMan4.setGender("男");

        LinkMan linkMan5 = new LinkMan();
        linkMan5.setName("大撒");
        linkMan5.setTel("16116");
        linkMan5.setGender("男");

        // 维护关系
        customer1.getLinkManSet().add(linkMan1);
        customer1.getLinkManSet().add(linkMan2);
        customer1.getLinkManSet().add(linkMan3);

        customer2.getLinkManSet().add(linkMan4);
        customer2.getLinkManSet().add(linkMan5);

        linkMan1.setCustomer(customer1);
        linkMan2.setCustomer(customer1);
        linkMan3.setCustomer(customer1);

        linkMan4.setCustomer(customer2);
        linkMan5.setCustomer(customer2);

        // 保存数据到数据库

        session.save(customer1);
        session.save(customer2);

        session.save(linkMan1);
        session.save(linkMan2);
        session.save(linkMan3);
        session.save(linkMan4);
        session.save(linkMan5);

        Customer customer3 = new Customer();
        customer3.setName("小米");
        customer3.setAddress("武汉");

        LinkMan linkMan6 = new LinkMan();
        linkMan6.setName("小蓝");
        linkMan6.setTel("156487");
        linkMan6.setGender("女");

        LinkMan linkMan7 = new LinkMan();
        linkMan7.setName("小绿");
        linkMan7.setTel("151213");
        linkMan7.setGender("女");

        customer3.getLinkManSet().add(linkMan6);
        customer3.getLinkManSet().add(linkMan7);

        linkMan6.setCustomer(customer3);
        linkMan7.setCustomer(customer3);

        session.save(customer3);


        HibernateUtil.release(session);
    }


    @Test
    public void testOneToManyDelete() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 3);
        session.delete(customer);

        transaction.commit();

        HibernateUtil.release(session);
    }

    @Test
    public void testOneToManyUpdate() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 2);
        LinkMan linkMan = session.get(LinkMan.class, 3);
        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);

        session.update(customer);

        transaction.commit();

        HibernateUtil.release(session);
    }

    @Test
    public void testManyToManySava() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        //准备数据
        Player player1 = new Player();
        player1.setName("王元圆");
        player1.setGender("男");

        Player player2 = new Player();
        player2.setName("猪猪公主");
        player2.setGender("女");

        Role role1 = new Role();
        role1.setName("射手");
        role1.setDescription("远程AD输出");

        Role role2 = new Role();
        role2.setName("辅助");
        role2.setDescription("保护我方输出");

        Role role3 = new Role();
        role3.setName("法师");
        role3.setDescription("远程AP输出");

        //维护数据关系
        player1.getRoles().add(role1);
        player1.getRoles().add(role2);

        player2.getRoles().add(role2);
        player2.getRoles().add(role3);

        //保存数据
        session.save(player1);
        session.save(player2);

        transaction.commit();

        HibernateUtil.release(session);
    }

    @Test
    public void testManyToManyDelete() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        Player player = session.get(Player.class, 2);
        session.delete(player);

        transaction.commit();

        HibernateUtil.release(session);
    }

    @Test
    public void testManyToManyUpdate() {
        Session session = HibernateUtil.openSession();

        Transaction transaction = session.beginTransaction();

        Player player = session.get(Player.class, 1);
//        Role role = session.get(Role.class, 3);
        Role role = session.get(Role.class, 2);

//        player.getRoles().add(role);
        player.getRoles().remove(role);

        transaction.commit();

        HibernateUtil.release(session);
    }
}
