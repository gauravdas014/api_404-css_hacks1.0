const Hospital = require('../models/hospitalModel');

exports.registerHospital = async (req, res) => {};

exports.getAllHospitals = async (req, res) => {
  try {
    const hospitals = await Hospital.find({ isApproved: true });
    res.status(200).json({
      status: 'success',
      hospitals,
    });
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.getHospital = async (req, res) => {
  //
};

exports.editHospitalDetails = async (req, res) => {
  //
};
