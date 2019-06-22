import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import AttachRoom from "./AttachRoom"
import {attachRoomGrid} from "./Actions";
import {connect} from 'react-redux';
import {detachRoomFromGrid} from "../US149/Actions149";

class AttachRoomToGrid extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      isHidden: true,
      formerGrid: '',
      name: '',
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value,
        isHidden: true
      });
    };
  }

  toggleHidden = () => this.setState((prevState) => ({isHidden: !prevState.isHidden}))

  handleSubmit() {
    this.props.onDeleteRoomFromGrid(this.state.name, this.state.formerGrid);
    this.props.onAttachRoomGrid(this.state.name, this.props.link.href);
    this.toggleHidden();
  }

  render() {
    const {name} = this.state;
    return (
      <>
        Name:<input value={this.state.name} placeholder="Ex: B107" type="text" name="name"
                      onChange={this.handleInputChange('name')}
      />
        Former Grid:<input value={this.state.formerGrid} placeholder="Ex: B building" type="text" name="formerGrid"
                           onChange={this.handleInputChange('formerGrid')}/>
        <p></p>
        <p>
          <small>(Please type the name of the room and its previous grid. If the room does not belong to a grid, just
            write its name.)
          </small>
        </p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}}
                onClick={this.handleSubmit}>Attach
          Room {name} to {this.props.grid}</Button>
        {!this.state.isHidden && <AttachRoom room={this.props.room} error={ this.props.error} grid={this.props.grid}/>}
      </>
    )
  }
}


const mapStateToProps = (state) => {
  return {
    // loading: state.ReducersGetRoomsNotInGrid.loading,
    // roomsNotInGrid: state.ReducersGetRoomsNotInGrid.roomsNotInGrid,
    // error: state.ReducersGetRoomsNotInGrid.error
    loading: state.Reducers147.loading,
    room: state.Reducers147.room,
    error: state.Reducers147.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onAttachRoomGrid: (name, link) => {
      dispatch(attachRoomGrid({name, link}))
    },
    onDeleteRoomFromGrid: (name, grid) => {
      dispatch(detachRoomFromGrid({name, grid}))
    },
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(AttachRoomToGrid);
