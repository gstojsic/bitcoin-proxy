package io.github.gstojsic.bitcoin.proxy.model;

import io.github.gstojsic.bitcoin.proxy.util.Pair;

/**
 * @param desc      (string, required) Descriptor to import
 * @param active    (boolean, optional, default=false) Set this descriptor to be the active descriptor for the corresponding output type/externality
 * @param range     (numeric or array) If a ranged descriptor is used, this specifies the end or the range (in the form [begin,end]) to import
 * @param nextIndex (numeric) If a ranged descriptor is set to active, this specifies the next index to generate addresses from
 * @param timestamp (integer / string, required) Time from which to start rescanning the blockchain for this descriptor, in UNIX epoch time
 * @param internal  (boolean, optional, default=false) Whether matching outputs should be treated as not incoming payments (e.g. change)
 * @param label     (string, optional, default="") Label to assign to the address, only allowed with internal=false. Disabled for ranged descriptors
 */
public record Descriptor(
        String desc,
        Boolean active,
        Pair<Integer, Integer> range,
        Integer nextIndex,
        String timestamp,
        Boolean internal,
        String label
) {
}

