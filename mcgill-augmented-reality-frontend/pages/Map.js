import React, { useEffect, useState } from 'react';
import { SafeAreaView, View, Text, StyleSheet, Dimensions } from 'react-native';
import MapView, { Marker } from 'react-native-maps';
import BottomSheet from '../components/CustomBottomSheet';
import SearchBar from '../components/SearchBar';
import fetchWrapper from '../api';
import { BACKEND } from '@env';

export default function Map() {
    const [locations, setLocations] = useState();
    const [aspectRatio, setAspectRatio] = useState(0);

    const region = {
        latitude: 45.5041,
        longitude: -73.5747,
        latitudeDelta: 0.005,
        longitudeDelta: 0.005,
    };

    useEffect(() => {
        const { width, height } = Dimensions.get('window');
        setAspectRatio(width / height);
        fetchWrapper(`${BACKEND}/building/get-all-mcgill-buildings`).then(
            (locations) => {
                setLocations(locations);
            }
        );
    }, []);

    return (
        <View style={styles.container}>
            <MapView
                style={styles.map}
                region={region}
                showsUserLocation={true}
            >
                {locations
                    ? locations.map((marker, index) => 
                          <Marker
                              key={index}
                              coordinate={{
                                  latitude: marker.location.latitude,
                                  longitude: marker.location.longitude,
                              }}
                              title={marker.name}
                              description={marker.location.address + ' ' + marker.location.postalCode}
                              image={require('../assets/crest.png')}
                          />
                    )
                    : null}
            </MapView>
            {locations ? <SearchBar locations={locations} /> : null}
            {/* {locations ? (
                <BottomSheet
                    location={{
                        name: locations[0].name,
                        ...locations[0].location,
                    }}
                />
            ) : null} */}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
    },
    map: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
    },
});