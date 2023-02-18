import { BACKEND } from '@env';
import React, { useEffect, useState, useRef } from 'react';
import { StyleSheet, View } from 'react-native';
import MapView, { Marker } from 'react-native-maps';
import fetchWrapper from '../api';
import SearchBar from '../components/SearchBar';

export default function Map() {
    const [locations, setLocations] = useState();
    const [desiredLocation, setDesiredLocation] = useState();

    const markerRef = useRef([]);

    const region = {
        latitude: 45.5041,
        longitude: -73.5747,
        latitudeDelta: 0.005,
        longitudeDelta: 0.005,
    };

    useEffect(() => {
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
                initialRegion={region}
                showsUserLocation={true}
                region={desiredLocation ? {latitude: desiredLocation.location.latitude, longitude: desiredLocation.location.longitude, ...region.location} : region}
            >
                {locations
                    ? locations.map((marker, index) => (
                          <Marker
                              ref={e => markerRef.current[index] = e}
                              key={index}
                              coordinate={{
                                  latitude: marker.location.latitude,
                                  longitude: marker.location.longitude,
                              }}
                              title={marker.name}
                              description={
                                  marker.location.address +
                                  ' ' +
                                  marker.location.postalCode
                              }
                              image={require('../assets/crest.png')}
                              onPress={() => {setDesiredLocation({name: marker.name, location: marker.location})}}
                          />
                      ))
                    : null}
            </MapView>
            {locations ? (
                <SearchBar
                    locations={locations}
                    setDesiredLocation={setDesiredLocation}
                    markerRef={markerRef}
                />
            ) : null}
            {/* {desiredLocation ? (
                <BottomSheet
                    location={{
                        name: desiredLocation.name,
                        ...desiredLocation.location,
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
