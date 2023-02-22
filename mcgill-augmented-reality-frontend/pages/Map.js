import { BACKEND } from '@env';
import React, { useEffect, useRef, useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import MapView, { Callout, Marker } from 'react-native-maps';
import fetchWrapper from '../api';
import BottomSheet from '../components/CustomBottomSheet';
import SearchBar from '../components/SearchBar';

export default function Map() {
    const [region, setRegion] = useState({
        latitude: 45.5041,
        longitude: -73.5747,
        latitudeDelta: 0.005,
        longitudeDelta: 0.005,
    });
    const [locations, setLocations] = useState();
    const [desiredLocation, setDesiredLocation] = useState({name: null, location: null});

    const markerRef = useRef([]);
    const mapRef = useRef(null);
    const bottomSheetRef = useRef(null);

    const setLocation = (locationObject) => {
        bottomSheetRef.current?.expand(); // open
        setDesiredLocation(locationObject);
        /*
        if we want to move the region to the center of the map
        setRegion( 
            {
                latitude: locationObject.location.latitude,
                longitude: locationObject.location.longitude,
                ...region.location,
            },
        )
        */
        mapRef.current.animateToRegion(
            {
                latitude: locationObject.location.latitude,
                longitude: locationObject.location.longitude,
                ...region.location,
            },
            400
        );
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
                                      shortCode: marker.shortCode
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
                    bottomSheetRef={bottomSheetRef}
                />
            ) : null}
            <BottomSheet
                ref={bottomSheetRef}
                location={{
                    name: desiredLocation.name,
                    shortCode: desiredLocation.shortCode,
                    ...desiredLocation.location,
                }}
            />
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
