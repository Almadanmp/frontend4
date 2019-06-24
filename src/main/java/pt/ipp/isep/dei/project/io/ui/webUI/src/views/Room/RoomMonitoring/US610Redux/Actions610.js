import axios from 'axios';

export const FETCH_ROOM_MAXT_STARTED = 'FETCH_ROOM_MAXT_STARTED';
export const FETCH_ROOM_MAXT_SUCCESS = 'FETCH_ROOM_MAXT_SUCCESS';
export const FETCH_ROOM_MAXT_FAILURE = 'FETCH_ROOM_MAXT_FAILURE';
export const FETCH_NO_DATA = 'FETCH_NO_DATA';


export function fetchMaxRoomTemp({href}) {
  console.log(href)
  const token = localStorage.getItem('loginToken');
  return dispatch => {
    dispatch(fetchMaxRoomTempStarted(href)); // antes de fazer o get, coloca o loading a true
    axios
      .get(href, {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          }
        }
      )
      .then(res => {
        dispatch(fetchMaxRoomTempSuccess(res.data)); // chegaram os resultados (dados) , loading fica a falso
      })
      .catch(err => {
        if (err.response === 400) {
          dispatch(fetchNoData(err.message))
        }
          else{
          dispatch(fetchMaxRoomTempFailure(err.message));
        }
      });
  };
}

export function fetchMaxRoomTempStarted(href) {
  return {
    type: FETCH_ROOM_MAXT_STARTED,
    payload: {
      href: href,
    }
  }
}

export function fetchMaxRoomTempSuccess(data) { // cria uma açao
  return {
    type: FETCH_ROOM_MAXT_SUCCESS,
    payload: {
      roomTemp: [...data] //passa o array com os dados
    }
  }
}

export function fetchMaxRoomTempFailure(message) {
  return {
    type: FETCH_ROOM_MAXT_FAILURE,
    payload: {
      error: message
    }
  }
}

export function fetchNoData(response) {
  return {
    type: FETCH_NO_DATA,
    payload: {
      errorData: response
    }
  }
}




