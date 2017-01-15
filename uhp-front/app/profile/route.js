import Ember from "ember";
import {Types} from "../models/types";

export default Ember.Route.extend({
  model (params) {
    return this.store.findRecord(Types.User, params.id).catch(() => ({}));
  }
});
