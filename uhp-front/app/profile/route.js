import Ember from "ember";
import {Types} from "../models/types";

export default Ember.Route.extend({
  model (params) {
    const record = this.store.createRecord(Types.User, {name: "Test User", email: "test@gmail.com"});
    record.save();
    return this.store.findRecord(Types.User, params.id).catch(() => ({}));
  }
});
