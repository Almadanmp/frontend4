import axios from 'axios';

export const REMOVE_GRID_ROOM_INFO_STARTED = 'REMOVE_GRID_ROOM_INFO_STARTED';
export const REMOVE_GRID_ROOM_INFO_SUCCESS = 'REMOVE_GRID_ROOM_INFO_SUCCESS';
export const REMOVE_GRID_ROOM_INFO_FAILURE = 'REMOVE_GRID_ROOM_INFO_FAILURE';


export const fetchRoom = ({name, floor, width, length, height}) => {
  return dispatch => {
    dispatch(fetchRoomInfo(name, floor, width, length, height)); // antes de fazer o get, coloca o loading a true
    const data = {name, floor, width, length, height};
    axios
      .post('http://localhost:9898/houseSettings/room', data, //falta autorização
        {
          headers: {'Content-Type': 'application/json'},
          body: {name, floor, width, length, height}
        })
      .then(res => {
        dispatch(fetchRoomInfoSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        dispatch(fetchRoomInfoFailure(err.message));
      });
  };
};


export function fetchRoomInfo(name, floor, width, length, height) {
  return {
    type: REMOVE_GRID_ROOM_INFO_STARTED,
    payload: {
      name: name,
      floor: floor,
      width: width,
      length: length,
      height: height
    }
  }
}

export function fetchRoomInfoSuccess(data) { // cria uma açao
  return {
    type: REMOVE_GRID_ROOM_INFO_SUCCESS,
    payload: {
      room: data //passa o array com os dados
    }
  }
}

export function fetchRoomInfoFailure(message) {
  return {
    type: REMOVE_GRID_ROOM_INFO_FAILURE,
    payload: {
      error: message
    }
  }
}