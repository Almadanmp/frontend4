import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
import {uploadFile} from "./ImportAreaReadingsActions";
import {Alert, Button} from "reactstrap";
import {connect} from "react-redux";
import {confirmAlert} from "react-confirm-alert";
import 'react-confirm-alert/src/react-confirm-alert.css'

class AreaReadingsDropzone extends Component {
  constructor(props) {
    super(props);
    this.state = {file: {}};
    this.onDrop = this.onDrop.bind(this);
    this.toggle = this.toggle.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  onDrop = (acceptedFiles) => {
    console.log(acceptedFiles);
    const formData = new FormData();

    let file = acceptedFiles[0]
    formData.append('file', file)
    formData.append('file', file.name)
    this.setState({file: formData})
  }

  handleSubmit = () => {
    if (this.state.file instanceof FormData) {
      confirmAlert({
        title: 'Confirm to import data',
        message: 'Are you sure to import ' + this.state.file.get('file').name + '?',
        buttons: [
          {
            label: 'Yes',
            onClick: () => this.props.onPostFile(this.state.file, this.props.link.href)
          },
          {
            label: 'No',
            onClick: () => {
            }
          }
        ]
      });
    } else {
      //TODO improve alert Box
      alert('Unable to submit: a file must be selected')
    }
  };

  render() {
    const maxSize = 1048576;
    return (
      <div className="text-center mt-5">
        <Dropzone
          onDrop={this.onDrop}
          accept="application/json, text/csv, text/xml"
          minSize={0}
          maxSize={maxSize}
        >
          {({getRootProps, getInputProps, isDragActive, isDragReject, rejectedFiles, acceptedFiles}) => {
            const isFileTooLarge = rejectedFiles.length > 0 && rejectedFiles[0].size > maxSize;
            return (
              <div {...getRootProps()}>
                <input {...getInputProps()} />
                {!isDragActive && <b>Click here or drop a file to upload!</b>}
                {isDragActive && !isDragReject && <b>Drop it!</b>}
                {isDragReject && <b>File type not accepted, sorry!</b>}
                {isFileTooLarge && (
                  <div className="text-danger mt-2">
                    File is too large.
                  </div>
                )}
                <ul className="list-group mt-2">
                  {acceptedFiles.length > 0 && acceptedFiles.map(acceptedFile => (
                    <li className="list-group-item list-group-item-success">
                      {acceptedFile.name}
                    </li>
                  ))}
                </ul>
                <div className="help-block"><Alert color="white">{this.props.importGAReadingsResults}</Alert></div>
              </div>

            )
          }
          }
        </Dropzone>
        <p></p>
        <p></p>
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Submit</Button>
      </div>

    );
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.ReducersAreaReadings.loading,
    error: state.ReducersAreaReadings.error,
    importGAReadingsResults: state.ReducersAreaReadings.importGAReadingsResults,
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onPostFile: (file, link) => {
      dispatch(uploadFile(file, link))
    }
  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AreaReadingsDropzone);
