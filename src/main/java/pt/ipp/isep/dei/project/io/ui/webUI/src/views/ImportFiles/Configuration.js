import React, {Component} from 'react';
import ImportOptions from "./ImportOptions";

class Configuration extends Component {
  constructor(props) {
    super(props);
    this.state = {
      collapse: false
    }
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Application Configuration Menu </h2>
        <p></p>
        <h5>Please select the option you want to run:</h5>
        <p></p>
        <p></p>
        <p></p>
        <ImportOptions/>
      </div>
    );
  }
}


export default Configuration;


