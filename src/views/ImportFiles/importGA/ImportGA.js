import React, {Component} from 'react';
import {Button, Card, CardBody, Collapse} from "reactstrap";
import GADropzone from "./GADropzone";

class ImportGA extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false, name: 'geographicAreasAndSensors'};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <Button onClick={this.toggle} style={{backgroundColor: '#93c4c4', marginBottom: '2rem', width: 250, height:50}}
                class="fa fa-plus-square-o fa-lg mt-4">Import geographic areas and sensors</Button>
        <Collapse isOpen={this.state.collapse}>
          <Card>
            <CardBody>
              <span>
              <GADropzone link={this.props.link}/>
              </span>
            </CardBody>
          </Card>
        </Collapse>
      </div>
    )
  }
}


export default ImportGA;


