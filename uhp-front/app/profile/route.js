import Ember from "ember";
import {Types} from "../models/types";
import AuthenticatedRouteMixin from "ember-simple-auth/mixins/authenticated-route-mixin";

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  sessionAccount: Ember.inject.service(),

  model () {
    const id = this.get('sessionAccount.account.id');
    return this.store.findRecord(Types.User, id).catch(() => ({}));
  }
});
