import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {inactivateSensorFromArea} from "../US010/Actions010";
import {connect} from 'react-redux';
import {confirmAlert} from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css


class AreaSensorInactivation extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      id: 0,
      sensorId: '',
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value
      });
    };

  }

  submit = () => {
    confirmAlert({
      title: 'Confirm inactivation',
      message: 'Are you sure to inactivate ' + this.state.sensorId + ' from ' + this.state.id + '?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => this.handleSubmit()
        },
        {
          label: 'No',
          onClick: () => {
          }
        }
      ]
    });
  };

  handleSubmit() {
    this.props.onInactivateSensorFromArea(this.state);
  }

  render() {
    return (
      <>

        <label> Geographic area Id:
          <input value={this.state.id} type="number" name="id" onChange={this.handleInputChange('id')}/>
        </label>

        <label> Area Sensor Id:
          <input value={this.state.sensorId} type="text" name="sensorId" placeholder="Sensor id" onChange={this.handleInputChange('sensorId')}/>
        </label>

        <p>{''}</p>

        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.submit}> Sensor inactivation</Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onInactivateSensorFromArea: ({id, sensorId}) => {
      dispatch(inactivateSensorFromArea({id, sensorId}))
    }
  }
};

export default connect(null, mapDispatchToProps)(AreaSensorInactivation);
