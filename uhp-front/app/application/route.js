import Ember from "ember";
import {Types} from "../models/types";

export default Ember.Route.extend({
  activate () {
    this.store.findRecord(Types.User, "admin");
  }
});
