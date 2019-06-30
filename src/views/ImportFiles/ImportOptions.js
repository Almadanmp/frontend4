import React, {Component} from 'react';
import ImportGA from "./importGA/ImportGA";
import ImportGAReadings from "./importAreaReadings/ImportGAReadings";
import ImportHouseReadings from "./importHouseReadings/ImportHouseReadings";
import ImportHouseSensors from "./importHouseSensors/ImportHouseSensors";
import ImportHouse from "./importHouse/ImportHouse";

class ImportOptions extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://smarthome-g2-server-v3.herokuapp.com/import/', {
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log)
    console.log(this.state.item);

  }

  render() {
    console.log(this.state.item)
    let {item} = this.state;
    if (item.length != 0) {
      return (
        <>
          <>
            <ImportGA link={item.find((hrefs) => hrefs.rel === 'Import Geographic Areas')}ImportGA/>
            <ImportGAReadings link={item.find((hrefs) => hrefs.rel === 'Import Area Sensor Readings')}/>
            <ImportHouse link={item.find((hrefs) => hrefs.rel === 'Import House Data')}/>
            <ImportHouseSensors link={item.find((hrefs) => hrefs.rel === 'Import House Sensors')}/>
            <ImportHouseReadings link={item.find((hrefs) => hrefs.rel === 'Import House Sensor Readings')}/>
          </>
        </>
      );
    }
    return (
      <>
      </>
    );
  }
}

export default ImportOptions;

