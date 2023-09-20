
package com.dev.utils;


import com.dev.objects.User;
import com.dev.objects.Message;
import com.dev.objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Persist {

    private final SessionFactory sessionFactory;

    @Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public User getUserByUsername (String username) {
        User found = null;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User where username = :username")
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return found;
    }

    public User getUserByToken (String token) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery
                ("FROM User where token = :token").setParameter
                ("token", token).uniqueResult();
        session.close();
        return user;
    }

    public User getUserByUsernameAndToken (String username, String token) {
        User found = null;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User where username = :username" +
                        " AND token = :token ").setParameter("username", username)
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public List<User> getAllUsers () {
        Session session = sessionFactory.openSession();
        List<User> allUsers = session.createQuery("From User").list();
        session.close();
        return allUsers;
    }


    public List<Message> getMessagesByToken (String token) {
        Session session = sessionFactory.openSession();
        List<Message> messages = session.createQuery
                        ("FROM Message where recipient.token = :token ")
                .setParameter("token", token)
                .list();
        session.close();
        return messages;
    }

    public List<Message> getConversation (String token, int recipientId) {
        Session session = sessionFactory.openSession();
        List<Message> messages = session.createQuery(
                        "FROM Message WHERE (sender.token = :token AND recipient.id = :id) " +
                                "OR " +
                                "(sender.id = :id2 AND recipient.token = :token2) ORDER BY id")
                .setParameter("token", token)
                .setParameter("token2", token)
                .setParameter("id", recipientId)
                .setParameter("id2", recipientId)
                .list();
        session.close();
        return messages;
    }

    public User getUserById (int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("FROM User where id = :id")
                .setParameter("id", id)
                .uniqueResult();
        session.close();
        return user;
    }

    public void saveMessage (Message message) {
        Session session = sessionFactory.openSession();
        session.save(message);
        session.close();
    }




//    public void addItemToDB(Item itemToAdd) {
//        Session session = sessionFactory.openSession();
//        session.save(itemToAdd);
//        session.close();
//
//
//    }
//
//
//    public List<Item> getAllItems() {
//        Session session = sessionFactory.openSession();
//        List<Item> items = session.createQuery("FROM Item ").list();
//        session.close();
//        return items;
//
//    }
//
//    public void addOfferToDb(Offer offerToAdd) {
//        Session session = sessionFactory.openSession();
//        session.save(offerToAdd);
//        session.close();
//
//    }
//
//    public void updateCredAfterOffer(String token, int userCredAfterOffer) {
//        User user = getUserByToken(token);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        user.setCred(userCredAfterOffer);
//        session.saveOrUpdate(user);
//        transaction.commit();
//        session.close();
//    }
//
//    public void updateCredByAdmin(String token, int adminsUpdate) {
//        User user = getUserByToken(token);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        user.setCred(adminsUpdate);
//        session.saveOrUpdate(user);
//        transaction.commit();
//        session.close();
//    }
//
//    public List<Offer> getAllOffers() {
//        Session session = sessionFactory.openSession();
//        List<Offer> offers = session.createQuery("FROM Offer ").list();
//        session.close();
//        return offers;
//
//    }
//
//    public void deleteOffers(List<Offer> offerListAfterFilterByItem) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        for (Offer i : offerListAfterFilterByItem) {
//            session.remove(i);
//        }
//        transaction.commit();
//        session.close();
//    }
//
//    public List<User> getAllUsers() {
//        Session session = sessionFactory.openSession();
//        List<User> users = session.createQuery("FROM User ").list();
//        session.close();
//        return users;
//    }
//
//    public void updateSysTotal(int payForSys) {
//        AdminSys adminSys = getById(1);
//        int temp;
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        temp = adminSys.getTotalBank() + payForSys;
//        adminSys.setTotalBank(temp);
//        session.saveOrUpdate(adminSys);
//        transaction.commit();
//        session.close();
//
//    }
//
//    public AdminSys getById(int id) {
//
//        AdminSys found = null;
//        Session session = sessionFactory.openSession();
//        found = (AdminSys) session.createQuery("FROM AdminSys WHERE id = :id")
//                .setParameter("id", id)
//                .uniqueResult();
//        session.close();
//        return found;
//    }
//    public Item getItemById(int id) {
//
//        Item found = null;
//        Session session = sessionFactory.openSession();
//        found = (Item)session.createQuery("FROM Item WHERE id = :id")
//                .setParameter("id", id)
//                .uniqueResult();
//        session.close();
//        return found;
//    }
//
//    public void updateItems(String closeOffer, int itemId) {
//        Item item = getItemById(itemId);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        item.setOpenOrClose(closeOffer);
//        session.saveOrUpdate(item);
//        transaction.commit();
//        session.close();
//
//
//    }
//
//
//
//
//    public Integer getOffersById(Integer itemId) {
//        Integer maxOffer = null;
//        Session session = sessionFactory.openSession();
//        List<Offer> offers = session.createQuery("FROM  Offer where itemId=:itemId order by amount DESC ")
//                .setParameter("itemId",itemId)
//                .list();
//
//        session.close();
//        if (!(offers.size()==0)){
//            maxOffer = offers.get(0).getAmount();
//        }
//        else {
//            maxOffer =0;
//        }
//        return maxOffer;
//    }
}

