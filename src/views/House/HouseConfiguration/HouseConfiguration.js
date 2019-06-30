import React, {Component} from 'react';
import US101Redux from './US101Redux/US101Redux';
import US108Redux from "./US108/US108Redux";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import {Button, Card, CardBody, CardHeader, Collapse} from "reactstrap";
import US105 from "./US105";

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
        <br></br>
        <Row>
          <Col xs="12" sm="10" md="8">
            <Card>
              <CardHeader>
                <strong>Rooms in the house</strong>
              </CardHeader>
              <CardBody>
                <US108Redux/>
                <US105 style={{float: 'left'}}/>
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col xs="6" sm="4" md="4">
            <Button onClick={this.toggle} style={{backgroundColor: '#FFFFFF', marginBottom: '1rem'}}>Configure the house
              location</Button>
            <Collapse isOpen={this.state.collapse}>
              <US101Redux/>
            </Collapse>
          </Col>
        </Row>
      </div>
    );
  }
}

export default HouseConfiguration;
