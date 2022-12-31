package io.github.gstojsic.bitcoin.proxy.model;

import java.util.List;
import java.util.Set;

public record MiningTemplate(List<String> capabilities, Set<String> rules) {
}
