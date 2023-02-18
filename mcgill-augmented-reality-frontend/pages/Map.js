import { BACKEND } from '@env';
import React, { useEffect, useRef, useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import MapView, { Marker, Callout } from 'react-native-maps';
import fetchWrapper from '../api';
import SearchBar from '../components/SearchBar';

export default function Map() {
    const [locations, setLocations] = useState();

    const setLocation = (locationObject) => {
        mapRef.current.animateToRegion(
            {
                latitude: locationObject.location.latitude,
                longitude: locationObject.location.longitude,
                ...region.location,
            },
            400
        );
    };
    const markerRef = useRef([]);
    const mapRef = useRef(null);

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
                ref={mapRef}
                style={styles.map}
                initialRegion={region}
                showsUserLocation={true}
            >
                {locations
                    ? locations.map((marker, index) => (
                          <Marker
                              ref={(e) => (markerRef.current[index] = e)}
                              key={index}
                              coordinate={{
                                  latitude: marker.location.latitude,
                                  longitude: marker.location.longitude,
                              }}
                              image={require('../assets/crest.png')}
                              onPress={() => {
                                  setLocation({
                                      name: marker.name,
                                      location: marker.location,
                                  });
                              }}
                          >
                              <Callout>
                                  <Text
                                      style={{
                                          color: '#CD202C',
                                          fontWeight: 'bold',
                                      }}
                                  >
                                      {marker.name}
                                  </Text>
                                  <Text>{marker.location.address}</Text>
                                  <Text>{marker.location.postalCode}</Text>
                              </Callout>
                          </Marker>
                      ))
                    : null}
            </MapView>
            {locations ? (
                <SearchBar
                    locations={locations}
                    setDesiredLocation={setLocation}
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
