const Hospital = require('../models/hospitalModel');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const generateOtp = require('../utils/generateOtp');

const signToken = (id) => {
  return jwt.sign({ id }, process.env.JWT_SECRET, {
    expiresIn: process.env.JWT_EXPIRES_IN,
  });
};

const createSendToken = async (user, statusCode, req, res) => {
  const token = signToken(user._id);

  res.cookie('jwt', token, {
    expires: new Date(
      Date.now() + process.env.JWT_COOKIE_EXPIRES_IN * 24 * 60 * 60 * 1000
    ),
    httpOnly: true,
    secure: req.secure || req.headers['x-forwarded-proto'] === 'https',
  });

  user.token = token;
  await user.save();
  user.password = undefined;
  res.status(statusCode).render('portal/dashboard');
};

exports.signup = async (req, res) => {
  try {
    const OTP = generateOtp.generateOtp(5);
    const newHospital = await Hospital.create({
      name: req.body.name,
      phone: req.body.phone,
      email: req.body.email,
      address: req.body.address,
      isApproved: false,
      password: req.body.password,
    });
    newHospital.password = await bcrypt.hash(req.body.password, 12);
    await newHospital.save();
    createSendToken(newHospital, 201, req, res);
  } catch (err) {
    console.log(err);
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};

exports.signin = async (req, res) => {
  try {
    const userId = req.body.userId;
    const password = req.body.password;
    const reg = /^\d+$/;
    if (reg.test(userId)) {
      const user = await User.findOne({ phone: userId }).select('+password');
      if (!user || !(await user.correctPassword(password, user.password))) {
        res.send('Username or password is incorrect');
      }
      res.status(200).json({
        status: 'success',
        user: user,
      });
    } else {
      const user = await User.findOne({ email: userId }).select('+password');
      if (!user || !(await user.correctPassword(password, user.password))) {
        res.send('Username or password is incorrect');
      }
      createSendToken(user, 200, req, res);
    }
  } catch (err) {
    res.status(400).json({
      status: 'fail',
      message: err,
    });
  }
};
