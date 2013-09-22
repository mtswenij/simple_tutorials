package models;

import java.util.*;

import siena.*;

public class BlackList extends Model {

	@Id(Generator.UUID)
	public String id;

	// the user
	@NotNull
	@Column("user_id")
	@Index("user_index")
	public User user;

	// the user
	@NotNull
	@Column("blacklist_id")
	@Index("blackList_index")
	public User blackList;

	public BlackList(User u, User b) {
		super();
		this.user = u;
		this.blackList = b;
	}

	public static Query<BlackList> all() {
		return Model.all(BlackList.class);
	}

	public static List<User> findBlackListByUser(User u) {
		List<BlackList> blkLists = all().filter("user", u).fetch();
		List<User> blUsers = new ArrayList<User>();
		for (BlackList bl : blkLists) {
			blUsers.add(bl.getBlackListed());
		}

		return blUsers;
	}

	public User getBlackListed() {
		return this.blackList;
	}

	public static List<User> findUserByBlackList(User u) {
		List<BlackList> blkLists = all().filter("blackList", u).fetch();
		List<User> users = new ArrayList<User>();
		for (BlackList bl : blkLists) {
			users.add(bl.getUserListed());
		}

		return users;
	}

	public User getUserListed() {
		return this.user;
	}

	public String toString() {
		return this.user + " : -" + this.blackList;
	}

	public static void deleteBlackListByUser(User u, User b) {
		BlackList blkListed = all().filter("user", u).filter("blackList", b)
				.get();
		blkListed.delete();
	}

	public static void deleteBlackListByID(String id) {
		BlackList blkListed = all().filter("id", id).get();
		blkListed.delete();
	}
}
