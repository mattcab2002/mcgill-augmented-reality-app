import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import FriendList from './components/FriendList';

export default function App() {
  return (
    <View style={{ flex: 1 }}>
      <FriendList />
    </View>
  );
}
