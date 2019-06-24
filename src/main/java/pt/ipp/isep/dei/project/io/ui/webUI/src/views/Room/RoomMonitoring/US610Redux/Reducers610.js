import {
  FETCH_ROOM_MAXT_STARTED,
  FETCH_ROOM_MAXT_SUCCESS,
  FETCH_ROOM_MAXT_FAILURE,
  FETCH_INTERNAL_ERROR,
  FETCH_NO_DATA

} from './Actions610'


const initialstate = {
    loading: false,
    error: null,
    roomTemp: []
};


export default function Reducers605 (state = initialstate, action) {
  switch (action.type) {
    case FETCH_ROOM_MAXT_STARTED:
      return {
        ...state,
          loading: true,
          error: null,
          roomTemp: []
      };
    case FETCH_ROOM_MAXT_SUCCESS:
      return {
        ...state,
          loading: false,
          error: null,
          roomTemp: [...action.payload.temp]
      };
    case FETCH_ROOM_MAXT_FAILURE:
      return {
        ...state,
          loading: false,
          error: action.payload.error,
          roomTemp: "ERROR: NO DATA Available"
      };
    case FETCH_NO_DATA:
      return {
        ...state,
        loading: false,
        errorData: action.payload.error,
        roomTemp: "ERROR: " + action.payload.error
      };
    default:
      return state;
  }
}


