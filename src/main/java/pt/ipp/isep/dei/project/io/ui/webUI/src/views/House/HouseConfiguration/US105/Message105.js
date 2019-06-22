import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";
import {attachRoomGrid} from "./Actions";

class Message105 extends React.Component {

  constructor(props) {
    super(props);
    //  this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      formerGrid: '',
      name: '',
    };
  }


  render() {
    const {room, name, floor, width, length, height} = this.props;
    if (room.toString().indexOf("422") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Invalid room dimensions. Please fix before continuing.</Alert></div>
        </div>
      )
    }
    else if ((room.toString()).indexOf("409") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">A Room with that name already exists.</Alert></div>
        </div>
      )
    }
    else if ((room.toString()).indexOf("400") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please complete every field before continuing.</Alert></div>
        </div>
      )
    } else {
      return (
        <div className="help-block"><Alert color="success">
          <p>The room was successfully created: </p>
          <ul>
            <li>
              Name: {name}
            </li>
            <li>
              Floor: {floor}
            </li>
            <li>
              Width: {width}
            </li>
            <li>
              Length: {length}
            </li>
            <li>
              Height: {height}
            </li>
          </ul>
        </Alert>
        </div>
      );
    }

  }
}

export default Message105;
