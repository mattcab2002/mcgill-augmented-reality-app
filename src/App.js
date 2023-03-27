import logo from './logo.svg';
import './App.css';

function App() {

  const [dots,setDots] = useState([])
  const [events, setEvents] = useState([])
  const [searchParams, setSearchParams] = useSearchParams();
  
  useEffect(() => {
    fetch("https://localhost:8080/routes/" + searchParams.get("routeId"))
      .then((response) => response.json())
      .then((data) => setDots(data));
    
    fetch("https://localhost:8080/events/")
      .then((response) => response.json())
      .then((data) => setEvents(data));
  }, []);


  return (
    <a-scene
      renderer="logarithmicDepthBuffer: true;"
      embedded
      loading-screen="enabled: false;"
      arjs="sourceType: webcam; debugUIEnabled: false;"
    >
      <a-assets>
        <a-asset-item
          id="animated-asset"
          src="assets/asset.gltf"
        ></a-asset-item>
      </a-assets>
      {dots.map((dot) => 
      <a-entity
        look-at="[gps-camera]"
        animation-mixer="loop: repeat"
        gltf-model="#animated-asset"
        scale="0.30000000000000016 0.30000000000000016 0.30000000000000016"
        gps-entity-place={"latitude:" + dot.latitude + ";"+ "latitude:"+ dot.latitude +";"}
      ></a-entity> ) }
      {events.map((event) =>(
        <a-entity
          look-at="[gps-camera]"
          animation-mixer="loop: repeat"
          text={"value:"+event.name+";"}
          scale="0.30000000000000016 0.30000000000000016 0.30000000000000016"
          gps-entity-place={"latitude:" + dot.latitude + ";"+ "latitude:"+ dot.latitude +";"}
        ></a-entity>))}

      <a-camera gps-camera rotation-reader></a-camera>
    </a-scene>
  );
}

export default App;
