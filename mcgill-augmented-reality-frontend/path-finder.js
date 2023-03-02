/**
 * get list of nodes to a distation from origin and destination
 * @param {*} origin object with longitude and latitude
 * @param {*} destination object with longitude and latitude
 */
export function getPath(origin, destination) {
    const og = '45.471165, -73.650087';
    const dc = '45.471249, -73.648020'
    fetch(`https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyDfvmLoVDttb7GFLrC3Z_0ssLjEhtlkxF0&origin=${og}&destination=${dc}&mode=walking`)
        .then(res => res.json())
        //well use this to do it, "steps" is how to walk there.
        .then(data => console.log(data.routes[0].legs[0].steps))
        .catch(err => console.error(err));
}

export default getPath;