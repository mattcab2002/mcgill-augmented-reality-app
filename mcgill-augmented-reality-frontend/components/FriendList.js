import React, { useState } from 'react';
import { View, Text, FlatList, StyleSheet } from 'react-native';

const FriendList = () => {
  const [friends, setFriends] = useState([
    { id: '1', name: 'John' },
    { id: '2', name: 'Sarah' },
    { id: '3', name: 'Jane' },
    { id: '4', name: 'Mike' },
  ]);

  const renderFriend = ({ item }) => (
    <View style={styles.friend}>
      <Text style={styles.friendName}>{item.name}</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.title}>My Friends List</Text>
      <FlatList
        data={friends}
        renderItem={renderFriend}
        keyExtractor={(item) => item.id}
        contentContainerStyle={styles.list}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    paddingTop: 50,
    fontSize: 24,
    fontWeight: 'bold',
    color: 'red',
    marginBottom: 10,
  },
  list: {
    flexGrow: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  friend: {
    backgroundColor: 'white',
    marginVertical: 10,
    padding: 10,
    borderRadius: 5,
    borderWidth: 1,
    borderColor: 'red',
  },
  friendName: {
    fontSize: 18,
    color: 'red',
    fontWeight: 'bold',
  },
});

export default FriendList;
