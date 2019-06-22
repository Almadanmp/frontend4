import React, {Component} from 'react';
import US101Redux from './US101Redux/US101Redux';
import US108Redux from "./US108/US108Redux";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import {Card, CardBody, CardHeader} from "reactstrap";

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
        <h2>Welcome to the House Configuration Menu</h2>
        <h4>Please select the option you want to run</h4>
        <Row>
          <Col xs="12" sm="10" md="5">
            <Card>
              <CardHeader>
                <strong>Rooms in the house</strong>
              </CardHeader>
              <CardBody>
                <US108Redux/>
              </CardBody>
            </Card>
          </Col>
          <Col xs="6" sm="4" md="4">
            <Card >
              <CardHeader>
                <strong>Configure the house location</strong>
              </CardHeader>
              <CardBody>
                <US101Redux/>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default HouseConfiguration;
