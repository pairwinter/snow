'use strict';

describe('Service: i18n', function () {

  // load the service's module
  beforeEach(module('snowApp'));

  // instantiate service
  var i18n;
  beforeEach(inject(function (_i18n_) {
    i18n = _i18n_;
  }));

  it('should do something', function () {
    expect(!!i18n).toBe(true);
  });

});
