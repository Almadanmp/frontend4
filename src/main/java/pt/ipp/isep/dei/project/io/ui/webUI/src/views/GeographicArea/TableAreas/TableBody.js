import React, {Component} from 'react';
import TableHeader from "./TableHeader";
import connect from "react-redux/es/connect/connect";
import GetChildren from "../ChildAreas/GetChildren.js";
import GetSensors from "../AreaSensors/GetSensors.js";
import {fetchGABTs} from '../US004/Actions004';

class TableBody extends Component {

  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchByTypes();
  }

  render() {
    const headers = {
      name: "Name",
      type: "Type",
      description: "Description",
      sensors: "Sensors",
      children: "Child Areas",
    };
    const {areas} = this.props;
    console.log(this.props.areas)
    return (
      <>
        <TableHeader headers={headers}/>
        {areas.map(item => (
          <tr key={item.id}>
            <td style={{
              textAlign: "center"
            }}> {item.name}</td>
            <td style={{
              textAlign: "center"
            }}>{item.typeArea} </td>
            <td style={{
              textAlign: "center"
            }}>{item.description} </td>
            <td style={{
              textAlign: "center"
            }}>
              <GetSensors link={item.links.find((hrefs) => hrefs.rel === 'List area sensors.')} area={item.name}
                          linkAdd={item.links.find((hrefs) => hrefs.rel === 'Add a new area sensors.')}/>
            </td>

            <td style={{
              textAlign: "center"
            }}>
              <GetChildren link={item.links.find((hrefs) => hrefs.rel === 'List child areas.')} area={item.name}
                           geographicAreaId={item.geographicAreaId}/>
            </td>
          </tr>
        ))}
      </>
    );
  }

}
const mapStateToProps = (state) => {
  return {
    areas: state.Reducers004.areas,
    listGATypes: state.Reducer002.listGATypes
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchByTypes: () => {
      dispatch(fetchGABTs())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TableBody);
