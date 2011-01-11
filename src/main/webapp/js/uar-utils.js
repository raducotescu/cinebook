/**
 * Helper function for the included Array.sort()
 */
function sortNumber(a, b) {
	return a - b;
}

/**
 * Function which returns the result of the subtraction method applied to sets
 * (mathematical concept).
 * 
 * @param a Array one
 * @param b Array two
 * @return An array containing the result
 */
function diffArray(a, b) {
	var seen = [], diff = [];
	for ( var i = 0; i < b.length; i++)
		seen[b[i]] = true;
	for ( var i = 0; i < a.length; i++)
		if (!seen[a[i]])
			diff.push(a[i]);
	return diff;
}

String.prototype.startsWith = function (s) {
	if(this.substring(0, s.length) == s) {
		return true;
	}
	return false;
}

String.prototype.endsWith = function (s) {
	if(this.substring(this.length - s.length, this.length) == s) {
		return true;
	}
	return false;
}

String.prototype.equalsIgnoreCase = function (s) {
	if(this.toUpperCase() == s.toUpperCase()) {
		return true;
	}
	return false;
}

String.prototype.replaceAll = function (a, b) {
	var s = this;
	while(s.indexOf(a) != -1) {
		s = s.replace(a, b);
	}
	return s;
}

String.prototype.isEmpty = function () {
	if(this == '') {
		return true;
	}
	return false;
}
