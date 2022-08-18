package com.misset.omt.qualitygate.issue;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;

public class OMTIssueLocation {

    private final int startLine;

    private final int endLine;

    private final int startLineOffset;

    private final int endLineOffset;

    public OMTIssueLocation(int startLine, int startLineOffset, int endLine, int endLineOffset) {
        this.startLine = startLine;
        this.startLineOffset = startLineOffset;
        this.endLine = endLine;
        this.endLineOffset = endLineOffset;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getStartLineOffset() {
        return startLineOffset;
    }

    public int getEndLineOffset() {
        return endLineOffset;
    }

    public TextRange toTextRange(InputFile inputFile) {
        return inputFile.newRange(startLine, startLineOffset, endLine, endLineOffset);
    }
}
