import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/address/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/address/sido/${params}`).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/address/gugun/${params}`).then(success).catch(fail);
}

function geoCode(params, success, fail) {
  api.get(`/address/dong/${params}`).then(success).catch(fail);
}

function interestList(params, success, fail) {
  api.get(`/address/interest/list/${params}`).then(success).catch(fail);
}

async function insertInterestAddr(json, success, fail) {
  console.log(json);
  await api.post(`address/interest`, json).then(success).catch(fail);
}
export {
  sidoList,
  gugunList,
  dongList,
  geoCode,
  interestList,
  insertInterestAddr,
};
