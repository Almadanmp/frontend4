import React, {Component} from 'react';
import RoomGrid from "./SmartGrid/RoomGrid"


class HouseConfiguration extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Energy Grid Menu.</h2>

        <p></p>
        <RoomGrid/>
        <p></p>




      </div>
    );
  }
}

export default HouseConfiguration;
