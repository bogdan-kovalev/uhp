import DS from "ember-data";

export default DS.JSONAPISerializer.extend({
  modelNameFromPayloadKey (key) {
    const modelName = this._super(key);
    return `resources/${modelName}`;
  },

  payloadKeyFromModelName (modelName) {
    return this._super(modelName.replace('resources/', ''));
  }
});
