import React, { useState } from 'react';
import { View, Text, FlatList, StyleSheet, TouchableOpacity } from 'react-native';

const FriendList = () => {
  const [friends, setFriends] = useState([
    { id: '1', name: 'John' },
    { id: '2', name: 'Sarah' },
    { id: '3', name: 'Jane' },
    { id: '4', name: 'Mike' },
  ]);

  const removeFriend = (id) => {
    
  };

  const renderFriend = ({ item }) => (
    <View style={styles.friendContainer}>
      <View style={styles.avatarContainer}>
        <Text style={styles.avatarText}>{item.name.charAt(0)}</Text>
      </View>
      <View style={styles.friendInfoContainer}>
        <Text style={styles.friendName}>{item.name}</Text>
        <Text style={styles.friendStatus}>Online</Text>
      </View>
      <TouchableOpacity onPress={() => removeFriend(item.id)}>
        <Text style={styles.deleteButton}>X</Text>
      </TouchableOpacity>
    </View>
  );

  return (
    <View style={styles.container}>
      <View style={styles.titleContainer}>
        <Text style={styles.titleText}>Friends List</Text>
      </View>
      <FlatList
        data={friends}
        renderItem={renderFriend}
        keyExtractor={(item) => item.id}
        contentContainerStyle={styles.listContainer}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
  titleContainer: {
    height: 95,
    paddingTop: 50,
    backgroundColor: '#CD202C',
    justifyContent: 'center',
    alignItems: 'center',
  },
  titleText: {
    color: '#FFFFFF',
    fontSize: 24,
    fontWeight: 'bold',
  },
  listContainer: {
    paddingBottom: 60,
  },
  friendContainer: {
    flexDirection: 'row',
    paddingHorizontal: 20,
    paddingVertical: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#EDEDED',
    alignItems: 'center',
  },
  avatarContainer: {
    width: 50,
    height: 50,
    borderRadius: 25,
    backgroundColor: '#FFAFAF',
    justifyContent: 'center',
    alignItems: 'center',
  },
  avatarText: {
    color: '#FFFFFF',
    fontSize: 24,
    fontWeight: 'bold',
  },
  friendInfoContainer: {
    flex: 1,
    marginLeft: 20,
  },
  friendName: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  friendStatus: {
    fontSize: 14,
    color: '#A0A0A0',
  },
  deleteButton: {
    borderRadius: 50,
    backgroundColor: '#FFAFAF',
    fontSize: 18,
    fontWeight: 'bold',
    marginLeft: 10,
    paddingHorizontal: 5
  }
});

export default FriendList;
