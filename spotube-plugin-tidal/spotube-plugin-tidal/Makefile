.PHONY: all build clean lint deps install help

PLUGIN_NAME   := spotube-plugin-tidal
ENTRY         := src/main.ht
OUT_DIR       := build
DIST_DIR      := dist
SMPLUG        := $(OUT_DIR)/plugin.smplug
VERSION       := $(shell python3 -c "import json; print(json.load(open('plugin.json'))['version'])" 2>/dev/null || echo "1.0.0")

# ── default ──────────────────────────────────────────────────────────────────
all: build

help:
	@echo "Spotube Tidal Plugin – build targets"
	@echo "  make deps    Install hetu_script_dev_tools + dart pub get"
	@echo "  make build   Compile .ht → build/plugin.smplug"
	@echo "  make lint    Syntax-check all .ht files"
	@echo "  make zip     Package build/ into a distributable .zip"
	@echo "  make install Copy plugin.smplug to SPOTUBE_PLUGIN_DIR"
	@echo "  make clean   Remove build/ and dist/"

# ── dependencies ─────────────────────────────────────────────────────────────
deps:
	dart pub global activate hetu_script_dev_tools
	dart pub get

# ── compile ───────────────────────────────────────────────────────────────────
build: $(SMPLUG)

$(SMPLUG): $(shell find src -name '*.ht')
	@mkdir -p $(OUT_DIR)
	hetu build $(ENTRY) -o $(SMPLUG)
	@echo "✅  Compiled → $(SMPLUG)  ($(shell wc -c < $(SMPLUG)) bytes)"

# ── lint ─────────────────────────────────────────────────────────────────────
lint:
	@for f in $$(find src -name '*.ht'); do \
	  echo "Checking $$f …"; \
	  hetu check $$f; \
	done
	@echo "✅  All files OK"

# ── package ───────────────────────────────────────────────────────────────────
zip: build
	@mkdir -p $(DIST_DIR)
	@DEST=$(DIST_DIR)/$(PLUGIN_NAME)-$(VERSION); \
	 mkdir -p $$DEST; \
	 cp $(SMPLUG) $$DEST/; \
	 cp plugin.json $$DEST/; \
	 [ -f README.md ] && cp README.md $$DEST/ || true; \
	 [ -f LICENSE   ] && cp LICENSE   $$DEST/ || true; \
	 cd $(DIST_DIR) && zip -r $(PLUGIN_NAME)-$(VERSION).zip $(PLUGIN_NAME)-$(VERSION)/; \
	 echo "✅  Packaged → $(DIST_DIR)/$(PLUGIN_NAME)-$(VERSION).zip"

# ── quick install (sideload for testing) ─────────────────────────────────────
install: build
ifndef SPOTUBE_PLUGIN_DIR
	$(error Set SPOTUBE_PLUGIN_DIR to your Spotube plugins folder)
endif
	cp $(SMPLUG) "$(SPOTUBE_PLUGIN_DIR)/tidal.smplug"
	cp plugin.json "$(SPOTUBE_PLUGIN_DIR)/tidal.plugin.json"
	@echo "✅  Installed to $(SPOTUBE_PLUGIN_DIR)"

# ── clean ─────────────────────────────────────────────────────────────────────
clean:
	rm -rf $(OUT_DIR) $(DIST_DIR)
	@echo "✅  Cleaned"
