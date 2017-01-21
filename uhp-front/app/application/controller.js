import Ember from "ember";

export default Ember.Controller.extend({
  navigator: Ember.inject.service(),
  session: Ember.inject.service(),

  actions: {
    invalidateSession() {
      this.get('session').invalidate();
    }
  }
});
