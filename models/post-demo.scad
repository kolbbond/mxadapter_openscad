union () {
  union () {
    translate ([3.1149999999999998, 0, 0]) {
      cube ([0.8, 2.53, 3.0], center=true);
    }
    translate ([-3.1149999999999998, 0, 0]) {
      cube ([0.8, 2.53, 3.0], center=true);
    }
  }
  translate ([0, 0, 1.8]) {
    union () {
      cube ([10.0, 8.0, 1.2], center=true);
      translate ([0, 0, -0.6]) {
        cube ([8.0, 0.4, 1.0], center=true);
      }
    }
  }
  translate ([0, 0, 4.2]) {
    union () {
      cube ([0.8, 4.4, 4.95], center=true);
      cube ([4.4, 0.8, 4.95], center=true);
    }
  }
}
