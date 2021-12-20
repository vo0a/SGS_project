import axios from "axios";

const BASE_URL = "http://localhost:8100/";
// const BASE_URL =
//   'http://ec2-.ap-northeast-2.compute.amazonaws.com/';

export const baseApi = axios.create({
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
});

// access token 을 항상 전달 해주세요
export const authenticatedApi = (token) =>
  axios.create({
    baseURL: BASE_URL,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });

export const authenticatedFormApi = (token) =>
  axios.create({
    baseURL: BASE_URL,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });
