import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import FriendList from './components/FriendList';
import NavigationBar from './components/NavigationBar';
import { NavigationContainer } from '@react-navigation/native';

export default function App() {
  return (
    <View style={{ flex: 1 }}>
      {/* <FriendList /> */}
      <NavigationContainer>
        <NavigationBar />
      </NavigationContainer>
    </View>
  );
}
