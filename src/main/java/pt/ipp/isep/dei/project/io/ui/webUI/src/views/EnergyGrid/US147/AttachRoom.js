import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert, Button} from "reactstrap";
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';
import {detachRoomFromGrid} from "../US149/Actions149";

class AttachRoom extends React.Component {

  constructor(props) {
    super(props);
  //  this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      name: '',
    };
  }


  render() {
    const {room, error} = this.props;

    if ((room.toString()).indexOf("ERROR") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">ERROR: {error}</Alert></div>

          </div>
        )}
        else
      {
        return (
          <><h6><i className="fa fa-check-square-o fa-lg"/> The room has been attached!</h6>
          </>
        );
      }

  }
}

export default AttachRoom;
