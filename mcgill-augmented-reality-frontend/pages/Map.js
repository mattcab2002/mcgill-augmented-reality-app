import React from 'react';
import { SafeAreaView,View, StyleSheet, Dimensions } from 'react-native';
import MapView from 'react-native-maps';
import SearchBar from '../components/SearchBar';

class Map extends React.Component {
    constructor(props) {
        super();
        this.state = {
          region:{
                latitude: 45.5041,
                longitude: -73.5747,
                latitudeDelta: 0.005,
                longitudeDelta: 0.005,
        }
      };
    }

    componentDidMount() {
        const { width, height } = Dimensions.get('window');
        this.aspectRatio = width / height;
    }

    render() {
        return (
            <SafeAreaView style={styles.container}>
                <MapView
                    style={styles.map}
                    region = {this.state.region}
                    showsUserLocation={true}
                />
                <SearchBar />
            </SafeAreaView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center'
    },
    map: {
      position: 'absolute',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0
    },
});

export default Map;
