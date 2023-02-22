import React, { useState } from 'react';
import { View, Text, FlatList, StyleSheet, TouchableOpacity, TextInput  } from 'react-native';
import { Ionicons } from '@expo/vector-icons'; 

const FriendList = () => {
  const [friends, setFriends] = useState([
    { id: '1', name: 'John', status: 'Online' },
    { id: '2', name: 'Sarah', status: 'Online' },
    { id: '3', name: 'Jane', status: 'Offline' },
    { id: '4', name: 'Mike', status: 'Offline' },
  ]);
  const [searchText, setSearchText] = useState('');
  
  const removeFriend = (id) => {
    setFriends((prevFriends) => prevFriends.filter((friend) => friend.id !== id));
  };

  const handleFriendRequest = () => {
    const newFriend = {
      id: String(Math.random()),
      name: 'New Friend',
    };
    setFriends([...friends, newFriend]);
  };

  const handleSearch = (text) => {
    setSearchText(text);
  };

  const filteredFriends = friends.filter((friend) =>
    friend.name.toLowerCase().includes(searchText.toLowerCase())
  );

  const renderFriend = ({ item }) => (
    <View style={styles.friendContainer}>
      <View style={styles.avatarContainer}>
        <Text style={styles.avatarText}>{item.name.charAt(0)}</Text>
      </View>
      <View style={styles.friendInfoContainer}>
        <Text style={styles.friendName}>{item.name}</Text>
        <Text style={styles.friendStatus}>{item.status}</Text>
      </View>
      <TouchableOpacity onPress={() => removeFriend(item.id)}>
        <Ionicons name="remove-circle" size={24} color="black" />
      </TouchableOpacity>
    </View>
  );

  return (
    <View style={styles.container}>
      <View style={styles.titleContainer}>
        <Text style={styles.titleText}>Friends</Text>
          <TouchableOpacity
            style={styles.addButton}
            onPress={handleFriendRequest}
          >
            <Text style={styles.addButtonText}>+</Text>
          </TouchableOpacity>
      </View>
      <TextInput
        style={styles.searchBar}
        placeholder="Search for friends"
        value={searchText}
        onChangeText={handleSearch}
      />
      <FlatList
        data={filteredFriends}
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
    flexDirection: 'row',
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
    textAlign: 'center',
    flex: 1,
    paddingLeft: 50,
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
  addButton: {
    backgroundColor: '#FFFFFF',
    borderWidth: 1,
    borderColor: '#CD202C',
    width: 30,
    height: 30,
    borderRadius: 15,
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: 18,
  },
  addButtonText: {
    color: '#CD202C',
    fontSize: 20,
    fontWeight: 'bold',
  },
  searchBar: {
    backgroundColor: '#F5F5F5',
    padding: 5,
    marginHorizontal: 10,
    marginVertical: 5,
    borderRadius: 5,
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
