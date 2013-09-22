package models;

import java.util.*;

import siena.*;

public class FavoriteList extends Model {
	@Id(Generator.UUID)
	public String id;

	// the user
	@NotNull
	@Column("user_id")
	@Index("user_index")
	public User user;

	// the user
	@NotNull
	@Column("favlist_id")
	@Index("favList_index")
	public User favoriteList;

	public FavoriteList(User u, User f) {
		super();
		this.user = u;
		this.favoriteList = f;
	}

	public static Query<FavoriteList> all() {
		return Model.all(FavoriteList.class);
	}

	public static List<User> findFavListByUser(User u) {
		List<FavoriteList> favLists = all().filter("user", u).fetch();
		List<User> flUsers = new ArrayList<User>();
		for (FavoriteList fl : favLists) {
			flUsers.add(fl.getFavListed());
		}

		return flUsers;
	}

	public User getFavListed() {
		return this.favoriteList;
	}

	public static List<User> findUserByFavList(User u) {
		List<FavoriteList> favLists = all().filter("favoriteList", u).fetch();
		List<User> users = new ArrayList<User>();
		for (FavoriteList fl : favLists) {
			users.add(fl.getUserListed());
		}

		return users;
	}

	public User getUserListed() {
		return this.user;
	}

	public String toString() {
		return this.user + " : +" + this.favoriteList;
	}

	public static void deleteFavListByUser(User u, User f) {
		FavoriteList favListed = all().filter("user", u)
				.filter("favoriteList", f).get();
		favListed.delete();
	}

	public static void deleteFavListByID(String id) {
		FavoriteList favListed = all().filter("id", id).get();
		favListed.delete();
	}
}
